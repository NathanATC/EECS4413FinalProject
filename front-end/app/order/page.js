"use client";
import { useLoginContext } from "../context/loginContext";

import React, { useState } from "react";

const Order = () => {
  const context = useLoginContext();

  async function placeOrder(username) {
    const convert = { method: "POST", username: username };
    const body = JSON.stringify(convert);
    const res = await fetch("http://localhost:8080/Backend/CheckoutServlet", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/x-www-form-urlencoded",
      }),
      body: body,
    });
  }

  const [cardNum, setCardNum] = useState("");
  const [cardExpire, setCardExpire] = useState("");
  const [cvc, setCvc] = useState("");

  const [firstname, setFirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [address, setAddress] = useState("");

  return (
    <>
      <div className="flex justify-center">
        <div className="flex flex-col gap-y-4 w-1/3">
          <div className="flex justify-center">
            <h1>Payment Info</h1>
          </div>
          <div className="flex justify-center">
            <div className="flex flex-col w-full">
              Card Number
              <input
                type="text"
                className="shadow appearance-none border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
          <div className="flex justify-center gap-x-4">
            <div className="flex flex-col">
              Expiry
              <input
                type="text"
                className="shadow appearance-none border rounded  py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
            <div className="flex flex-col">
              CVC
              <input
                type="text"
                className="shadow appearance-none border rounded  py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
          <div className="flex justify-center">
            <h1>Shipping</h1>
          </div>
          <div className="flex justify-center">
            <div className="flex flex-col w-full">
              Country
              <input
                type="text"
                className="shadow appearance-none border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
          <div className="flex justify-center">
            <div className="flex flex-col w-full">
              Address
              <input
                type="text"
                className="shadow appearance-none border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
          <button>Submit Order!</button>
        </div>
      </div>

      <button
        onClick={async () => {
          await placeOrder(context.userContext.username);
        }}
      >
        {" "}
        <b>place order</b>{" "}
      </button>
    </>
  );
};

export default Order;
