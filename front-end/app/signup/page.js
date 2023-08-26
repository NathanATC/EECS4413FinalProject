"use client";
import React, { useState } from "react";

const SignUp = () => {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [address, setAddress] = useState("");
  const [phonenumber, setPhonenumber] = useState("");
  const [province, setProvince] = useState("");
  const [country, setCountry] = useState("");
  const [billingaddress, setBillingaddress] = useState("");
  const [postalCode, setPostalCode] = useState("");
  const [email, setEmail] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  const permissions = "Customer";

  function validate() {
    if (!username) {
      setErrorMsg("Enter Username!");
      return false;
    }
    if (!password) {
      setErrorMsg("Enter Password!");
      return false;
    }
    if (!firstname) {
      setErrorMsg("Enter First Name!");
      return false;
    }
    if (!lastname) {
      setErrorMsg("Enter Last Name!");
      return false;
    }
    if (!address) {
      setErrorMsg("Enter Address!");
      return false;
    }
    if (!phonenumber) {
      setErrorMsg("Enter Phone Number!");
      return false;
    }
    if (!province) {
      setErrorMsg("Enter Province!");
      return false;
    }
    if (!country) {
      setErrorMsg("Enter Country!");
      return false;
    }
    if (!billingaddress) {
      setErrorMsg("Enter Billing Address!");
      return false;
    }
    if (!postalCode) {
      setErrorMsg("Enter Postal Code!");
      return false;
    }
    if (!email) {
      setErrorMsg("Enter Email!");
      return false;
    }
    return true;
  }

  async function createAccount() {
    var bodyMap = new Map();
    bodyMap.set("username", username);
    bodyMap.set("firstName", firstname);
    bodyMap.set("lastName", lastname);
    bodyMap.set("phoneNumber", phonenumber);
    bodyMap.set("province", province);
    bodyMap.set("Country", country);
    bodyMap.set("billingAddress", billingaddress);
    bodyMap.set("address", address);
    bodyMap.set("postalCode", postalCode);
    bodyMap.set("email", email);
    bodyMap.set("password", password);
    bodyMap.set("premissions", "Customer");
    const body = JSON.stringify(Object.fromEntries(bodyMap));
    console.log(body);
    const res = await fetch("http://localhost:8080/Backend/CreateAccount", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/x-www-form-urlencoded",
      }),
      body: body,
    });
    if (res.status == 200) {
      alert("Account Created!");
    } else {
      alert("Please try again!");
    }
  }

  return (
    <>
      <div className="flex flex-col gap-y-4">
        <h6 className="flex justify-center">Create a new Account!</h6>
        {errorMsg ? <p>{errorMsg}</p> : null}
        <div className="flex flex-row justify-center">
          <div className="flex flex-col gap-y-4">
            <label>
              Username:
              <input
                type="text"
                name="username"
                onInput={(e) => setUserName(e.target.value)}
              />
            </label>
            <label>
              Password:
              <input
                type="text"
                name="password"
                onInput={(e) => setPassword(e.target.value)}
              />
            </label>
            <label>
              First Name:
              <input
                type="text"
                name="firstname"
                onInput={(e) => setFirstname(e.target.value)}
              />
            </label>
            <label>
              Last Name:
              <input
                type="text"
                name="lastname"
                onInput={(e) => setLastname(e.target.value)}
              />
            </label>
            <label>
              Address :
              <input
                type="text"
                name="address"
                onInput={(e) => setAddress(e.target.value)}
              />
            </label>
            <label>
              Phone Number:
              <input
                type="text"
                name="phonenumber"
                onInput={(e) => setPhonenumber(e.target.value)}
              />
            </label>
          </div>
          <div className="flex flex-col gap-y-4">
            <label>
              Province:
              <input
                type="text"
                name="province"
                onInput={(e) => setProvince(e.target.value)}
              />
            </label>
            <label>
              Country:
              <input
                type="text"
                name="country"
                onInput={(e) => setCountry(e.target.value)}
              />
            </label>
            <label>
              Billing Address:
              <input
                type="text"
                name="billingaddress"
                onInput={(e) => setBillingaddress(e.target.value)}
              />
            </label>
            <label>
              Postal Code:
              <input
                type="text"
                name="postalCode"
                onInput={(e) => setPostalCode(e.target.value)}
              />
            </label>
            <label>
              Email:
              <input
                type="text"
                id="email"
                onInput={(e) => setEmail(e.target.value)}
              />
            </label>
          </div>
        </div>
        <button
          onClick={() => {
            if (validate()) {
              setErrorMsg(null);
              createAccount();
            }
          }}
        >
          Create Account!
        </button>
      </div>
    </>
  );
};

export default SignUp;
