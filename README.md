ParameterizedTest
![image](https://user-images.githubusercontent.com/48466124/220831401-e48814ea-7482-4fd2-9731-024fdf385928.png)

RepeatedTest
![image](https://user-images.githubusercontent.com/48466124/220831494-7ee41050-92be-40cf-bc21-f55dd46eb5d7.png)


**Author:** Maftun Hashimli

**Version:** 0.0.1-SNAPSHOT

# Class Name：com.findoutmyloan.application.customer.controller.CustomerController

## 1.&nbsp;

**URL:** http://localhost/api/v1/customer/

**Type:** GET

**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Request-parameters:**

None

**Response-fields:**

Field|Type|Description
---|---|---
┌─&nbsp; |object|&nbsp;
├── data|object|&nbsp;
│&nbsp;&nbsp; ├── value|object|&nbsp;
│&nbsp;&nbsp; ├── serializationView|object|&nbsp;
│&nbsp;&nbsp; └── filters|object|&nbsp;
├── responseDate|string|&nbsp;
├── isSuccess|boolean|&nbsp;
└── message|string|&nbsp;

**Response-example:**
``` json
{
  "data": {
    "value": {}
  },
  "message": "XyCD5",
  "responseDate": "Aug 17, 2016, 5:55:19 AM",
  "isSuccess": false
}
```

**Curl-example:**
``` bash
curl -X GET -i http://localhost/api/v1/customer/
```

## 2.&nbsp;

**URL:** http://localhost/api/v1/customer/

**Type:** DELETE

**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Request-parameters:**

None

**Response-fields:**

Field|Type|Description
---|---|---
┌─&nbsp; |object|&nbsp;
├── data|object|&nbsp;
├── responseDate|string|&nbsp;
├── isSuccess|boolean|&nbsp;
└── message|string|&nbsp;

**Response-example:**
``` json
{
  "data": {},
  "message": "i",
  "responseDate": "Feb 2, 1996, 10:02:59 AM",
  "isSuccess": false
}
```

**Curl-example:**
``` bash
curl -X DELETE -i http://localhost/api/v1/customer/
```

## 3.&nbsp;

**URL:** http://localhost/api/v1/customer/

**Type:** PUT

**Content-Type:** application/json

**Body-parameters:**

Parameter|Type|Required|DefaultValue|Description
---|---|---|---|---
└── customerUpdateRequestDTO|object|true|&nbsp;|
&nbsp;&nbsp; ├── monthlyIncome|float|&nbsp;|0.0|&nbsp;
&nbsp;&nbsp; ├── baseAdditionalFieldsCreatedDate|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── baseAdditionalFieldsUpdatedDate|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── name|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── surname|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── identityNo|int64|&nbsp;|0|&nbsp;
&nbsp;&nbsp; ├── birthDate|string|&nbsp;|&nbsp;|Pattern: yyyy-MM-dd
&nbsp;&nbsp; ├── phoneNumber|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; └── personType|enum|&nbsp;|&nbsp;|CUSTOMER<br/>SURETY&nbsp;

**Request-body-example:**
``` json
{
  "identityNo": 0,
  "baseAdditionalFieldsUpdatedDate": "Nov 25, 1970, 9:21:59 PM",
  "phoneNumber": "cv",
  "surname": "Q6hFgYt6",
  "baseAdditionalFieldsCreatedDate": "Aug 11, 1985, 8:19:05 PM",
  "name": "IN",
  "personType": "CUSTOMER",
  "birthDate": "2077-05-18",
  "monthlyIncome": 0.0
}
```

**Response-fields:**

Field|Type|Description
---|---|---
┌─&nbsp; |object|&nbsp;
├── data|object|A DTO for the<br/>entity&nbsp;
│&nbsp;&nbsp; ├── monthlyIncome|float|&nbsp;
│&nbsp;&nbsp; ├── customerLimit|float|&nbsp;
│&nbsp;&nbsp; ├── baseAdditionalFieldsCreatedDate|string|&nbsp;
│&nbsp;&nbsp; ├── baseAdditionalFieldsUpdatedDate|string|&nbsp;
│&nbsp;&nbsp; ├── name|string|&nbsp;
│&nbsp;&nbsp; ├── surname|string|&nbsp;
│&nbsp;&nbsp; ├── identityNo|int64|&nbsp;
│&nbsp;&nbsp; ├── birthDate|string|Pattern: yyyy-MM-dd
│&nbsp;&nbsp; ├── phoneNumber|string|&nbsp;
│&nbsp;&nbsp; └── personType|enum|CUSTOMER<br/>SURETY&nbsp;
├── responseDate|string|&nbsp;
├── isSuccess|boolean|&nbsp;
└── message|string|&nbsp;

**Response-example:**
``` json
{
  "data": {
    "identityNo": 0,
    "baseAdditionalFieldsUpdatedDate": "Apr 26, 2018, 5:07:07 PM",
    "phoneNumber": "A3",
    "surname": "1BSiRbonHr",
    "customerLimit": 0.0,
    "baseAdditionalFieldsCreatedDate": "Jun 17, 1981, 8:11:52 PM",
    "name": "0NJ3J",
    "personType": "CUSTOMER",
    "birthDate": "2095-09-04",
    "monthlyIncome": 0.0
  },
  "message": "IKZi",
  "responseDate": "Aug 6, 2097, 5:30:33 PM",
  "isSuccess": false
}
```

**Curl-example:**
``` bash
curl -X PUT -H 'Content-Type: application/json' -i http://localhost/api/v1/customer/ --data '{
  "identityNo": 0,
  "baseAdditionalFieldsUpdatedDate": "Nov 25, 1970, 9:21:59 PM",
  "phoneNumber": "cv",
  "surname": "Q6hFgYt6",
  "baseAdditionalFieldsCreatedDate": "Aug 11, 1985, 8:19:05 PM",
  "name": "IN",
  "personType": "CUSTOMER",
  "birthDate": "2077-05-18",
  "monthlyIncome": 0.0
}'
```

## 4.&nbsp;

**URL:** http://localhost/api/v1/customer/{identityNo}/{birthday}/find-loans

**Type:** GET

**Content-Type:** application/x-www-form-urlencoded;charset=UTF-8

**Path-parameters:**

Parameter|Type|Required|DefaultValue|Description
---|---|---|---|---
├── identityNo|int64|true|0|
└── birthday|string|true|&nbsp;|

**Response-fields:**

Field|Type|Description
---|---|---
┌─&nbsp; |object|&nbsp;
├── data|array&lt;object&gt;|&nbsp;
│&nbsp;&nbsp; ├── paybackGuaranteeType|enum|SURETY<br/>COLLATERAL<br/>ALL_OF_THEM&nbsp;
│&nbsp;&nbsp; ├── amount|float|&nbsp;
│&nbsp;&nbsp; ├── result|enum|APPROVED<br/>REJECTED&nbsp;
│&nbsp;&nbsp; ├── baseAdditionalFieldsCreatedDate|string|&nbsp;
│&nbsp;&nbsp; └── baseAdditionalFieldsUpdatedDate|string|&nbsp;
├── responseDate|string|&nbsp;
├── isSuccess|boolean|&nbsp;
└── message|string|&nbsp;

**Response-example:**
``` json
{
  "data": [
    {
      "result": "APPROVED",
      "amount": 0.0,
      "paybackGuaranteeType": "COLLATERAL",
      "baseAdditionalFieldsUpdatedDate": "Dec 28, 2069, 10:22:30 AM",
      "baseAdditionalFieldsCreatedDate": "May 16, 2007, 3:11:50 PM"
    }
  ],
  "message": "RsbQ",
  "responseDate": "Jun 6, 2072, 8:09:47 PM",
  "isSuccess": false
}
```

**Curl-example:**
``` bash
curl -X GET -i http://localhost/api/v1/customer/0/Thu Feb 21 17:24:27 TRT 2008/find-loans
```