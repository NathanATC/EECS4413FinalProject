"use client";
import React, { useState, useEffect } from "react";
import { useCartContext } from "../context/cartContext";

const shoppingCart = () => {
  const [data, setData] = useState([]);

  async function fetchData() {
    const convert = { method: "GET", username: "jimmy123" };
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
    fetchData();
  }, []);

  if (data == 0 || data == null) {
    return <div>No Items In Cart!</div>;
  }
  return (
    <>
      {data.map((item) => {
        return (
          <>
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
              <h1 className="flex justify-center">
                <b>x{item.currentQuantity}</b>
              </h1>
            </div>
          </>
        );
      })}
    </>
  );
};

export default shoppingCart;
