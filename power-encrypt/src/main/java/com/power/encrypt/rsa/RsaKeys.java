package com.power.encrypt.rsa;

/**
 * rsa加解密用的公钥和私钥
 * @author Administrator
 *
 */
public class RsaKeys {

	//生成秘钥对的方法可以参考这篇帖子
	//https://www.cnblogs.com/yucy/p/8962823.html

//	//服务器公钥
//	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Dw9nwjBmDD/Ca1QnRGy"
//											 + "GjtLbF4CX2EGGS7iqwPToV2UUtTDDemq69P8E+WJ4n5W7Iln3pgK+32y19B4oT5q"
//											 + "iUwXbbEaAXPPZFmT5svPH6XxiQgsiaeZtwQjY61qDga6UH2mYGp0GbrP3i9TjPNt"
//											 + "IeSwUSaH2YZfwNgFWqj+y/0jjl8DUsN2tIFVSNpNTZNQ/VX4Dln0Z5DBPK1mSskd"
//											 + "N6uPUj9Ga/IKnwUIv+wL1VWjLNlUjcEHsUE+YE2FN03VnWDJ/VHs7UdHha4d/nUH"
//											 + "rZrJsKkauqnwJsYbijQU+a0HubwXB7BYMlKovikwNpdMS3+lBzjS5KIu6mRv1GoE"
//											 + "vQIDAQAB";
//
//	//服务器私钥(经过pkcs8格式处理)
//	private static final String serverPrvKeyPkcs8 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoPD2fCMGYMP8J"
//				 								 + "rVCdEbIaO0tsXgJfYQYZLuKrA9OhXZRS1MMN6arr0/wT5YniflbsiWfemAr7fbLX"
//				 								 + "0HihPmqJTBdtsRoBc89kWZPmy88fpfGJCCyJp5m3BCNjrWoOBrpQfaZganQZus/e"
//				 								 + "L1OM820h5LBRJofZhl/A2AVaqP7L/SOOXwNSw3a0gVVI2k1Nk1D9VfgOWfRnkME8"
//				 								 + "rWZKyR03q49SP0Zr8gqfBQi/7AvVVaMs2VSNwQexQT5gTYU3TdWdYMn9UeztR0eF"
//				 								 + "rh3+dQetmsmwqRq6qfAmxhuKNBT5rQe5vBcHsFgyUqi+KTA2l0xLf6UHONLkoi7q"
//				 								 + "ZG/UagS9AgMBAAECggEBANP72QvIBF8Vqld8+q7FLlu/cDN1BJlniReHsqQEFDOh"
//				 								 + "pfiN+ZZDix9FGz5WMiyqwlGbg1KuWqgBrzRMOTCGNt0oteIM3P4iZlblZZoww9nR"
//				 								 + "sc4xxeXJNQjYIC2mZ75x6bP7Xdl4ko3B9miLrqpksWNUypTopOysOc9f4FNHG326"
//				 								 + "0EMazVaXRCAIapTlcUpcwuRB1HT4N6iKL5Mzk3bzafLxfxbGCgTYiRQNeRyhXOnD"
//				 								 + "eJox64b5QkFjKn2G66B5RFZIQ+V+rOGsQElAMbW95jl0VoxUs6p5aNEe6jTgRzAT"
//				 								 + "kqM2v8As0GWi6yogQlsnR0WBn1ztggXTghQs2iDZ0YkCgYEA/LzC5Q8T15K2bM/N"
//				 								 + "K3ghIDBclB++Lw/xK1eONTXN+pBBqVQETtF3wxy6PiLV6PpJT/JIP27Q9VbtM9UF"
//				 								 + "3lepW6Z03VLqEVZo0fdVVyp8oHqv3I8Vo4JFPBDVxFiezygca/drtGMoce0wLWqu"
//				 								 + "bXlUmQlj+PTbXJMz4VTXuPl1cesCgYEA6zu5k1DsfPolcr3y7K9XpzkwBrT/L7LE"
//				 								 + "EiUGYIvgAkiIta2NDO/BIPdsq6OfkMdycAwkWFiGrJ7/VgU+hffIZwjZesr4HQuC"
//				 								 + "0APsqtUrk2yx+f33ZbrS39gcm/STDkVepeo1dsk2DMp7iCaxttYtMuqz3BNEwfRS"
//				 								 + "kIyKujP5kfcCgYEA1N2vUPm3/pNFLrR+26PcUp4o+2EY785/k7+0uMBOckFZ7GIl"
//				 								 + "FrV6J01k17zDaeyUHs+zZinRuTGzqzo6LSCsNdMnDtos5tleg6nLqRTRzuBGin/A"
//				 								 + "++xWn9aWFT+G0ne4KH9FqbLyd7IMJ9R4gR/1zseH+kFRGNGqmpi48MS61G0CgYBc"
//				 								 + "PBniwotH4cmHOSWkWohTAGBtcNDSghTRTIU4m//kxU4ddoRk+ylN5NZOYqTxXtLn"
//				 								 + "Tkt9/JAp5VoW/41peCOzCsxDkoxAzz+mkrNctKMWdjs+268Cy4Nd09475GU45khb"
//				 								 + "Y/88qV6xGz/evdVW7JniahbGByQhrMwm84R9yF1mNwKBgCIJZOFp9xV2997IY83S"
//				 								 + "habB/YSFbfZyojV+VFBRl4uc6OCpXdtSYzmsaRcMjN6Ikn7Mb9zgRHR8mPmtbVfj"
//				 								 + "B8W6V1H2KOPfn/LAM7Z0qw0MW4jimBhfhn4HY30AQ6GeImb2OqOuh3RBbeuuD+7m"
//				 								 + "LpFZC9zGggf9RK3PfqKeq30q";

	//服务器公钥
	private static final String serverPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1LfooraTcCImVqL4oDKc\n" +
			"dyAo47yF/bBWUNIE4x2iF4inWSLPTj3mM/7KJaiezW7eceAG4ZS3HPpH0MxfFNST\n" +
			"YvY3ePfAlQ7gLmlG9iiTMv0o5HvtHI88gOhc+tXlQx0tab2b7idhnwYUW4quJjkg\n" +
			"Sds9vmtgK0L+6XnSb45GjwK8dNcUrBMP0d/V/QGmlGNvGcOciGv9V9fXPOYns/yn\n" +
			"GIcvQ6qAjmuUYCrkkFhrKihDijA0vxXcURX4yxO/wuwRL7I9YeSC5n6gX1bOdu4k\n" +
			"PZwBg2q1GdtA50aI30NVDBP6SUv/JwluzMHRR7OAbJDIWqRxErfpQ9phGfuxvLyB\n" +
			"0QIDAQAB";

	//服务器私钥(经过pkcs8格式处理)
	private static final String serverPrvKeyPkcs8 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDUt+iitpNwIiZW\n" +
			"ovigMpx3ICjjvIX9sFZQ0gTjHaIXiKdZIs9OPeYz/solqJ7Nbt5x4AbhlLcc+kfQ\n" +
			"zF8U1JNi9jd498CVDuAuaUb2KJMy/Sjke+0cjzyA6Fz61eVDHS1pvZvuJ2GfBhRb\n" +
			"iq4mOSBJ2z2+a2ArQv7pedJvjkaPArx01xSsEw/R39X9AaaUY28Zw5yIa/1X19c8\n" +
			"5iez/KcYhy9DqoCOa5RgKuSQWGsqKEOKMDS/FdxRFfjLE7/C7BEvsj1h5ILmfqBf\n" +
			"Vs527iQ9nAGDarUZ20DnRojfQ1UME/pJS/8nCW7MwdFHs4BskMhapHESt+lD2mEZ\n" +
			"+7G8vIHRAgMBAAECggEANTLBRWAvw01nFSew0i+WHdy3HS1avTVKgO/lNP+GOCh+\n" +
			"4bXo7oJgfgRsfbqL7IibrRL/5Y6RtPeK2lciOE1phshY2zHqwdx0iGbfzlQbbBcQ\n" +
			"EQRFKm1QDlUOW9T+fyhe4SEATDEhIuocUUB/iSOkRZRE/shPxsH8iltjG28TilQK\n" +
			"F9Bk0AtLhRl2K6BKtwAwncpfOUhRqto+ijjxeqgOHx7XSEkE90ST152s8C6f1C/Z\n" +
			"pRebTCmHO9c8D2Km25RFvgiThP6jkNb1/SXvRd8Q9eMuUADdrY869+gndMztcOO2\n" +
			"9RS8EomVs46phftzczJXSU70qDTA2e+9bOKoDTQlOQKBgQDvKfNogP85fgjGwVu3\n" +
			"Hb3UdhrIixGVcJ0D/ijFAmR0QQj1M5+yq2wtDTdw1L65kpLnu0FBEDS9k91fjFqk\n" +
			"wjF707LNq19r6h1PNcyWRGM6rORrNw1J4D5NVbddyOhDSlzSV0jjBuOGwFhFbxF5\n" +
			"UdLhPYrz1751hTynj6YShH6fewKBgQDjsWAd/qeSOKWuyFblgmjh3Q9+TsSaJdVP\n" +
			"MUC3kzzoScepkRV/4Slm8jZzPDdiw7qA/61gRZ+vSjGCTmTJ72ev2EqCdnp1vZUY\n" +
			"/l6SmtfHCCPTWedvj4FEDXcP5RYnQG6nWuKCkXSnteA0ZkDafFWW9gQvXaCP2Tjd\n" +
			"jHUYhW3cIwKBgGmeLx5BiXTMUffkQCqX+dMv7RgPQk6qkszGmx6VteMZLWmLyMTQ\n" +
			"q08h/Q/ZA/lT34hyy5h+Haxy9lpR013bXJsAnH/zZIp1s0fdzGG4BdJNs/odL8Mm\n" +
			"Qp1Ic1f5d4jv+Bnz7f5Edop1uwW+zQq0tdo+oXSaHEQt+OFOsNqQpJKtAoGAPalH\n" +
			"clgIYVzd4Zt3jOfoJ2KKU5ZZP7WHMs8Py6cgE3gHjjAwNlVvtOVbQZHJHgILe+vB\n" +
			"tBSmzf21sqgwO0+OMNzOMM6NE12oBwC788/jJ49YxF/QgTHHu/o5iCBuA2s7jC7c\n" +
			"sKdbhB+I/CfE4GZTd3JgZ8NfPBWDhNwIyf84ZSkCgYEAh2Hjc1JNjSsB7UiWX1AC\n" +
			"Tfap2ueK74RYjisr+SuNgWpIdWjf0EqWEbDuSrElURuNzgetfe+kn/Nngd2YJrLN\n" +
			"0psf4q4ub8dWTWF6vtdzLJr6lKSBuU9MNROtk6oXLKsCK44a/Pq/xJcDqL1G3Y06\n" +
			"1ag6n7oHuExmIw7RLokO5s0=";

	public static String getServerPubKey() {
		return serverPubKey;
	}

	public static String getServerPrvKeyPkcs8() {
		return serverPrvKeyPkcs8;
	}
	
}
