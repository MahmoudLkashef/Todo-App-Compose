package com.mahmoudreda.todolistcompose.auth

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class SignUpTest{


    @Test
    fun`signup with username start with number then return false`(){
        //arrange
        val username="1mahmoud"

        //act
        val actualResult=isUsernameValid(username)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with username start with special char then return false`(){
        //arrange
        val username="%mahmoud"

        //act
        val actualResult=isUsernameValid(username)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with username less than 3 chars then return false`(){
        //arrange
        val username="ma"

        //act
        val actualResult=isUsernameValid(username)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with valid username then return true`(){
        //arrange
        val username="mahmoud"

        //act
        val actualResult=isUsernameValid(username)

        //assert
        assertTrue(actualResult)
    }

    private fun isUsernameValid(username: String): Boolean {
        return if(username.length < 3)false
        else if(!username.first().isLetter())false
        else true
    }

    @Test
    fun`signup with empty email then return false`(){
        //arrange
        val email=""

        //act
        val actualResult=isEmailValid(email)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with email start with special char then return false`(){
        //arrange
        val email="@mahmoud@gmail.com"

        //act
        val actualResult=isEmailValid(email)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with email that have invalid domain then return false`(){
        //arrange
        val email="mahmoud@g.com"

        //act
        val actualResult=isEmailValid(email)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with email that have valid domain then return true`(){
        //arrange
        val email="mahmoud@yahoo.com"

        //act
        val actualResult=isEmailValid(email)

        //assert
        assertTrue(actualResult)
    }



    private fun isEmailValid(email: String): Boolean {
        val validDomains= listOf("gmail.com", "yahoo.com", "hotmail.com", "outlook.com")
        return if(email.isEmpty())false
        else if(!email.first().isLetter())false
        //abd@o@gmail.com
        // gmail.com
        else if(!validDomains.contains(email.substringAfterLast('@')))false
        else true
    }

    @Test
    fun`signup with empty phone number then return false`(){
        //arrange
        val phoneNumber=""

        //act
        val actualResult=isPhoneNumberValid(phoneNumber)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with phone number less than 11 chars then return false`(){
        //arrange
        val phoneNumber="120"

        //act
        val actualResult=isPhoneNumberValid(phoneNumber)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with phone number that not started with 01 then return false`(){
        //arrange
        val phoneNumber="23456789123"

        //act
        val actualResult=isPhoneNumberValid(phoneNumber)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with phone number with length more than 11 return false`(){
        //arrange
        val phoneNumber="0145678912310"

        //act
        val actualResult=isPhoneNumberValid(phoneNumber)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with valid phone number then return true`(){
        //arrange
        val phoneNumber="01018251768"

        //act
        val actualResult=isPhoneNumberValid(phoneNumber)

        //assert
        assertTrue(actualResult)
    }

    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return if(phoneNumber.length != 11)false
        else if(!phoneNumber.startsWith("01"))false
        else true
    }

    @Test
    fun`signup with password less than 8 chars then return false`(){
        //arrange
        val password="123"

        //act
        val actualResult=isPasswordValid(password)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with password that not have special char then return false`(){
        //arrange
        val password="12345678"

        //act
        val actualResult=isPasswordValid(password)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with password that not have upper case char then return false`(){
        //arrange
        val password="12345678:"

        //act
        val actualResult=isPasswordValid(password)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with password that not have numbers then return false`(){
        //arrange
        val password="abcdefgasA:"

        //act
        val actualResult=isPasswordValid(password)

        //assert
        assertFalse(actualResult)
    }

    @Test
    fun`signup with valid password then return true`(){
        //arrange
        val password="asdfghjkl:R1"

        //act
        val actualResult=isPasswordValid(password)

        //assert
        assertTrue(actualResult)
    }


    private fun isPasswordValid(password: String): Boolean {
        val specialCharRegex = ("[!-\\/:-@\\[-`\\{-~]").toRegex()
        val upperCaseCharRegex=("[A-Z]").toRegex()
        val numbersRegex=("[0-9]").toRegex()

        return if(password.length <8)false
        else if(!password.any { it.toString().matches(specialCharRegex) })false
        else if(!password.any { it.toString().matches(upperCaseCharRegex) })false
        else if(!password.any { it.toString().matches(numbersRegex) })false
        else true
    }

}

