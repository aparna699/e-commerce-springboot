package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Items;
import com.example.ecommerce.entity.Review;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.ReviewRepository;

@RestController
@RequestMapping("/")
public class ReviewController {
	@Autowired
	private ReviewRepository  reviewRepository;
	
	//get all
	@GetMapping("api/review")
	public List<Review> getAllReview(){
		return this.reviewRepository.findAll();
	}
	
	//get by user id
	@GetMapping("api/review/item/{itemId}")
	public List<Review> getReviewByItemId(@PathVariable(value = "itemId") long  itemId){
		Items item = new Items(itemId);
		return this.reviewRepository.findByItemId(item);
	}
	
	//put
	@PutMapping("api/review/{reviewId}")
	public Review editReview(@RequestBody Review review, @PathVariable(value = "reviewId") long  reviewId) {
		Review existingReview = this.reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ reviewId));
		existingReview.setRate(review.getRate() != 0 ?
				review.getRate(): existingReview.getRate());
		existingReview.setTitle(review.getTitle() != null ?
				review.getTitle(): existingReview.getTitle());
		existingReview.setComment(review.getComment() != null ?
				review.getComment(): existingReview.getComment());
		existingReview.setDate(review.getDate() != null ?
				review.getDate(): existingReview.getDate());
		
		return this.reviewRepository.save(existingReview);
	}
	
	//post
	@PostMapping("api/review")
	public Review createReview(@RequestBody Review review) {
		return this.reviewRepository.save(review);
	}
	
	//delete
	@DeleteMapping("api/review/{reviewId}")
	public ResponseEntity<Review> deleteReview(@PathVariable(value = "reviewId") long  reviewId) {
		Review existingReview = this.reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id:"+ reviewId));
		this.reviewRepository.delete(existingReview);
		return ResponseEntity.ok().build();
	}
	
}
