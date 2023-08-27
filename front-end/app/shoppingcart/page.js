"use client";
import React, { useState, useEffect } from "react";
import { useLoginContext } from "../context/loginContext";
import Link from "next/link";

const ShoppingCart = () => {
  const [data, setData] = useState([]);
  const [total, setTotal] = useState(0);

  const context = useLoginContext();

  async function fetchData(username) {
    const convert = { method: "GET", username: username, qty: 0 };
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
  }

  useEffect(() => {
    getTotal();
  }, [data]);

  useEffect(() => {
    if (context.userContext.username) {
      fetchData(context.userContext.username);
    }
  }, [context.userContext.username]);

  function getTotal() {
    if (data.length != 0) {
      setTotal(
        data
          .map((item) => item.currentQuantity * item.price)
          .reduce((a, b) => a + b)
      );
    } else {
      setTotal(0);
    }
  }

  async function deleteFromCart(username) {
    const convert = { method: "CLEAR", username: username, qty: 0 };
    const body = JSON.stringify(convert);
    const res = await fetch("http://localhost:8080/Backend/Cart", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/x-www-form-urlencoded",
      }),
      body: body,
    });
    const json = res.json();
    console.log(json);
  }

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
        <div className="bg-[#bfdbfe] border-2 border-[#93c5fd] w-1/4">
          {data.map((item) => {
            return (
              <>
                <div className="flex flex-row justify-around">
                  <div
                    className="flex flex-col justify-center h-32 w-32"
                    key={item.name}
                  >
                    <h1 className="flex justify-start">
                      <b>{item.itemName}</b>
                    </h1>
                    <p className="flex justify-start">
                      {Intl.NumberFormat("en-US", {
                        style: "currency",
                        currency: "CAD",
                      }).format(item.price)}
                    </p>
                  </div>
                  {item.image == null ? (
                    <div className="h-32 w-32">Could not load Img</div>
                  ) : (
                    <div className="flex justify-center">
                      <img src={item.image} className="h-32 w-32" alt="Error" />
                    </div>
                  )}
                  <h1 className="flex flex-col justify-center w-8">
                    <b>x{item.currentQuantity}</b>
                  </h1>
                </div>
              </>
            );
          })}
          <div className="flex flex-row justify-around">
            <p>Total: ${Intl.NumberFormat("en-US").format(total)}</p>
          </div>
          <div className="flex flex-row justify-around">
            <button
              className="rounded-full bg-[#60a5fa] px-4 py-2"
              onClick={async () => {
                if (context.userContext.username) {
                  await deleteFromCart(context.userContext.username);
                  await fetchData(context.userContext.username);
                }
              }}
            >
              <b>Clear Cart!</b>
            </button>
            <Link href="/order" passHref>
              <button className="rounded-full bg-[#60a5fa] px-4 py-2">
                <b>Checkout!</b>
              </button>
            </Link>
          </div>
        </div>
      </div>
    </>
  );
};

export default ShoppingCart;
