package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.example.ecommerce.dto.CreatePayment;
import com.example.ecommerce.dto.CreatePaymentResponse;
import com.google.gson.Gson;



@RestController
@RequestMapping("/")
public class PaymentController {

	//Payment Intent
	@PostMapping("create-payment-intent")
	public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException  {
		Stripe.apiKey = "sk_test_51O2ptpSDQwswy4FijwKtrXt1cPIsS5AnUkVvYfeKFUtMNDCU8So7jCjKCxYYhGWWK2YyNpyFlbBuDw8vIwCZFjFS006ukgqyoH";      
		PaymentIntentCreateParams params =
		        PaymentIntentCreateParams.builder()
		          .setAmount(createPayment.getAmount() * 100)
		          .setCurrency("inr")
		      // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
		          .setAutomaticPaymentMethods(
		            PaymentIntentCreateParams.AutomaticPaymentMethods
		              .builder()
		              .setEnabled(true)
		              .build()
		          )
		          .build();

		      // Create a PaymentIntent with the order amount and currency
		      PaymentIntent paymentIntent = PaymentIntent.create(params);
		      return new CreatePaymentResponse(paymentIntent.getClientSecret());
	}
	
	//PaymentMethod
	
	//Cancel Payment Intent
	@PostMapping("cancel-payment-intent/{clientSecret}")
	public String cancelPaymentIntent(@PathVariable(value = "clientSecret") String  clientSecret) throws StripeException {
		Stripe.apiKey = "sk_test_51O2ptpSDQwswy4FijwKtrXt1cPIsS5AnUkVvYfeKFUtMNDCU8So7jCjKCxYYhGWWK2YyNpyFlbBuDw8vIwCZFjFS006ukgqyoH";

		// To create a PaymentIntent, see our guide at: https://stripe.com/docs/payments/payment-intents/creating-payment-intents#creating-for-automatic
		PaymentIntent paymentIntent =
		  PaymentIntent.retrieve(clientSecret);

		PaymentIntent updatedPaymentIntent =
		  paymentIntent.cancel();
		return "Canceled";
	}
}
