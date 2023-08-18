"use client";

import React, { createContext, useContext, useState } from "react";

export const cartContext = createContext();

export const CartContextProvider = ({ children }) => {
  const [shoppingCartContext, setShoppingCartContext] = useState([]);
  console.log("cart context :" + shoppingCartContext);
  return (
    <cartContext.Provider
      value={{ shoppingCartContext, setShoppingCartContext }}
    >
      {children}
    </cartContext.Provider>
  );
};

export const useCartContext = () => useContext(cartContext);
