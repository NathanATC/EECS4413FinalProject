"use client";
import React, { useState, useEffect } from "react";
import { useCartContext } from "../context/cartContext";

const shoppingCart = () => {
  const { shoppingCartContext, setShoppingCartContext } = useCartContext();
  if (shoppingCartContext.length == 0 || shoppingCartContext == null) {
    return <div>No Items In Cart!</div>;
  }
  return (
    <>
      {shoppingCartContext.map((item) => {
        return (
          <>
            <div key={item.name}>
              <h1 className="flex justify-center">
                <b>{item.name}</b>
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
          </>
        );
      })}
    </>
  );
};

export default shoppingCart;
