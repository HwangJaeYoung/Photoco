package com.continueing.photoco.reuse.etc;

public interface ErrorCodeList {
	String ERROR_MESSAGE_UNKNOWN = "This error is not identified. We will fix this kind of error";
	String ERROR_MESSAGE_USERNAME_ABSENCE = "username is absent";
	String ERROR_MESSAGE_EMAIL_ABSENCE = "email is absent";
	String ERROR_MESSAGE_PASSWORD_ABSENCE = "password is absent";
	String ERROR_MESSAGE_PASSWORD_CONFIRMATION_ABSENCE = "password confirmation is absent";
	String ERROR_MESSAGE_LOCATION_ID_ABSENCE = "location id is absent";
	String ERROR_MESSAGE_TOO_SHORT_USERNAME = "username is too short, should be more than 2 letter";
	String ERROR_MESSAGE_TOO_LONG_USERNAME = "username is too long, should be less than 16 letter";
	String ERROR_MESSAGE_TOO_SHORT_PASSWORD = "password is too short, should be more than 5 letter";
	String ERROR_MESSAGE_TOO_LONG_PASSWORD = "password is too long, should be less than 21 letter";
	String ERROR_MESSAGE_DIFFERENT_PASSWORDS = "password and password confirmation are not identical";
	String ERROR_MESSAGE_INVALID_EMAIL = "email format is not valid, it should look such as (mail_id@hostname.domain)";
	String ERROR_MESSAGE_INVALID_PRIMARY_KEY = "primary key 'id' is not valid, it should be positive number";
	String ERROR_MESSAGE_NONEXISTENT_LOCATION_ID = "location id does not exist. can not find from database";
	String ERROR_MESSAGE_ALREADY_EXISTING_USER = "already signed up user, so that username or email is duplicated";
	String ERROR_MESSAGE_USERNAME_PASSWORD_MISMATCH = "can not login. check username or password";
	String ERROR_MESSAGE_IMAGE_ABSENCE = "image file is absent";
	String ERROR_MESSAGE_CATEGORY_ID_ABSENCE = "category id is absent";
	String ERROR_MESSAGE_DURATION_HOUR_ABSENCE = "duration hour is absent";
	String ERROR_MESSAGE_TAG_NAMES_ABSENCE = "tag names are absent";
	String ERROR_MESSAGE_DESCRIPTION_ABSENCE = "description is absent";
	String ERROR_MESSAGE_NONEXISTENT_CATEGORY_ID = "category id does not exist. can not find from database";
	String ERROR_MESSAGE_LOGIN_REQUIRED = "you should login if you want to use this API";
	String ERROR_MESSAGE_INVALID_JSON_STRING = "json in post parameter is not valid. Format the data";

	String ERROR_MESSAGE_CHECKBOTTUN_NOT_SELECTED = "Check button isn't selected, so you should check this 'check button' to sign up";
}