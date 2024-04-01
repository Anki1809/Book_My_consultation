package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.User;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.service.AppointmentService;
import com.upgrad.bookmyconsultation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserAdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentService appointmentService;

	/**
	 post login ->  http://localhost:8080/auth/login
	 BASIC AUTH
	 username-> ankit@gmail.com
	 password -> ankit

	 Response
	 {
	 "id": "ankit@gmail.com",
	 "firstName": "Ankit",
	 "lastName": "Gupta",
	 "emailAddress": "ankit@gmail.com",
	 "mobilePhoneNumber": "9028546157",
	 "lastLoginTime": null,
	 "accessToken": "eyJraWQiOiIwNDBkNmI0MC0xYzQxLTRlZWUtOTdhYy1kODgyYzhjM2EzZGUiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJhbmtpdEBnbWFpbC5jb20iLCJpc3MiOiJodHRwczovL2Jvb2tteWNvbnN1bHRhdGlvbi5jb20iLCJleHAiOjE2NDU5MTUsImlhdCI6MTY0NTg4N30.l1qi-UkCUdY6U2bkmSwLK8LKGyYq7y_mbtcCX0mjmBujdDfj4Pi-0dqA7jAMcxcNpMLC69MX3k1TzCnW-YF5jg"
	 }

	 http get ->
	 * @param accessToken
	 * @param userUuid
	 * @return
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUser(@RequestHeader("authorization") String accessToken,
	                                    @PathVariable("id") final String userUuid) {
		final User User = userService.getUser(userUuid);
		return ResponseEntity.ok(User);
	}
	
	//create a post method named createUser with return type as ResponseEntity
		//define the method parameter user of type User. Set it final. Use @RequestBody for mapping.
		//declare InvalidInputException using throws keyword
		
		//register the user
	
		//return http response with status set to OK

	/**
	 POST REQUEST
	 http://localhost:8080/users/register
	 request body json
	 {
	 "emailId" : "ankit12@gmail.com",
	 "firstName" : "Ankitaa",
	 "lastName" : "Guptaaa",
	 "dob" : "1997-09-28",
	 "mobile" : "9028589157",
	 "password" : "ankit12"
	 }
	 response body json
	 {
	 "emailId": "ankit12@gmail.com",
	 "firstName": "Ankitaa",
	 "lastName": "Guptaaa",
	 "dob": "1997-09-28",
	 "mobile": "9028589157",
	 "password": "89FF61623F238B34",
	 "createdDate": "2022-02-26",
	 "salt": "wxKL4seNQq2IBITv1blg9LetqFhRDYLZcqRRQa+B6XQ="
	 }
	 * @param user
	 * @return
	 * @throws InvalidInputException
	 */

	@PostMapping("/register")
	public ResponseEntity createUser(@RequestBody final User user) throws InvalidInputException {
		User registeredUser = userService.register(user);
		return new ResponseEntity(registeredUser, HttpStatus.OK);
	}



	@GetMapping("/{userId}/appointments")
	public ResponseEntity getAppointmentForUser(@PathVariable("userId") String userId) {
		return ResponseEntity.ok(appointmentService.getAppointmentsForUser(userId));
	}


}
