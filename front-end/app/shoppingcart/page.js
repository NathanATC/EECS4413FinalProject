"use client";
import React, { useState, useEffect } from "react";
import { useLoginContext } from "../context/loginContext";

const shoppingCart = () => {
  const [data, setData] = useState([]);

  const context = useLoginContext();

  async function fetchData(username) {
    const convert = { method: "GET", username: username };
    const body = JSON.stringify(convert);
    const res = await fetch("http://localhost:8080/Backend/Cart", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/x-www-form-urlencoded",
      }),
      body: body,
    });
    const fetchedData = await res.json();
    setData(fetchedData);
    console.log(fetchedData);
  }

  useEffect(() => {
    console.log(context.userContext.username);
    if (context.userContext.username) {
      fetchData("jimmy123");
    }
  }, []);
  // Load error if not logged in
  if (!context.userContext.username) {
    return <div>Login to manage cart!</div>;
  }
  if (data == 0 || data == null) {
    return <div>No Items In Cart!</div>;
  }
  return (
    <>
      <div>
        <h1>Current Cart </h1>
        {data.map((item) => {
          return (
            <>
              <div className="flex flex-row">
                <div key={item.name}>
                  <h1 className="flex justify-center">
                    <b>{item.itemName}</b>
                  </h1>
                  {item.image == null ? (
                    <div className="h-32 w-32">Could not load Img</div>
                  ) : (
                    <div className="flex justify-center">
                      <img src={item.image} className="h-32 w-32" alt="Error" />
                    </div>
                  )}
                  <p className="flex justify-center">
                    {Intl.NumberFormat("en-US", {
                      style: "currency",
                      currency: "CAD",
                    }).format(item.price)}
                  </p>
                </div>
                <h1 className="flex justify-center">
                  <b>x{item.currentQuantity}</b>
                </h1>
              </div>
            </>
          );
        })}
      </div>
    </>
  );
};

export default shoppingCart;
