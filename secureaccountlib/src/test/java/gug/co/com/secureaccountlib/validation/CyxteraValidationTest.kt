package gug.co.com.secureaccountlib.validation

import gug.co.com.secureaccountlib.data.validate.IValidationData
import gug.co.com.secureaccountlib.data.validate.cyxtera.CyxteraValidationData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CyxteraValidationTest {

    private lateinit var validationData: IValidationData

    @Before
    fun setupValidation() {

        validationData = CyxteraValidationData()

    }

    @Test
    fun userName_Empty() {
        val userName = ""
        Assert.assertEquals(false, validationData.validateUserName(userName))
    }

    @Test
    fun userName_notMail() {
        val userName = "Hola"
        Assert.assertEquals(false, validationData.validateUserName(userName))
    }

    @Test
    fun userName_wrongMail() {
        val userName = "hola@hola"
        Assert.assertEquals(false, validationData.validateUserName(userName))
    }

    @Test
    fun userName_mail() {
        val userName = "jcmodev12@gmail.com"
        Assert.assertEquals(true, validationData.validateUserName(userName))
    }

    @Test
    fun password_2char() {
        val password = "bu"
        Assert.assertEquals(false, validationData.validatePassword(password))
    }

    @Test
    fun password_7char() {
        val password = "bububub"
        Assert.assertEquals(false, validationData.validatePassword(password))
    }

    @Test
    fun password_8char_onlyNumbers() {
        val password = "12345678"
        Assert.assertEquals(false, validationData.validatePassword(password))
    }

    @Test
    fun password_8char_onlyUpper() {
        val password = "JUANCAMA"
        Assert.assertEquals(false, validationData.validatePassword(password))
    }

    @Test
    fun password_8char_onlyLower() {
        val password = "juancama"
        Assert.assertEquals(false, validationData.validatePassword(password))
    }

    @Test
    fun password_8char_special() {
        val password = "juan-cama"
        Assert.assertEquals(false, validationData.validatePassword(password))
    }

    @Test
    fun password_chevere() {
        val password = "JkVillavo12-Col"
        Assert.assertEquals(true, validationData.validatePassword(password))
    }

    @Test
    fun password_chevere2() {
        val password = "-Col-JkVillavo12"
        Assert.assertEquals(true, validationData.validatePassword(password))
    }@Test

    fun password_cyxtera() {
        val password = "Cyxtera2019-USA"
        Assert.assertEquals(true, validationData.validatePassword(password))
    }
}