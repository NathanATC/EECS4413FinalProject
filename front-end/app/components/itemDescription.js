import React, { useState } from "react";
import { useCartContext } from "../context/cartContext";

const ItemDescriptionPopup = ({ item, isVisible, onClose }) => {
  const { shoppingCartContext, setShoppingCartContext } = useCartContext();

  const addItem = () => {
    console.log(shoppingCartContext);
    setShoppingCartContext([...shoppingCartContext, item]);
  };

  if (!isVisible) return null;
  return (
    <>
      <div className="fixed inset-0 bg-black bg-opacity-25 backdrop-blur-sm flex justify-center items-center">
        <div className="flex flex-col w-1/3">
          <div className="flex flex-col bg-white p-2 rounded gap-y-4">
            <button onClick={() => onClose()}>X</button>
            <div className="flex flex-col">
              <div className="flex flex-row justify-center">
                <img src={item.image} className="h-32 w-32" />
              </div>
              <div className="flex flex-row justify-around">
                <div className="flex flex-col">
                  <p>
                    <b>Name</b>: {item.name}
                  </p>
                  <p>
                    <b>Description</b>: {item.description}
                  </p>
                  <p>
                    <b>Category</b>: {item.category}
                  </p>
                  <p>
                    <b>Amount In Stock</b>: {item.stock}
                  </p>
                  <p>
                    <b>Price</b>:
                    {Intl.NumberFormat("en-US", {
                      style: "currency",
                      currency: "CAD",
                    }).format(item.price)}
                  </p>
                </div>
                <div className="flex flex-col">
                  <button
                    className="rounded-full bg-[#60a5fa] px-4 py-2"
                    onClick={() => {
                      addItem();
                      onClose();
                    }}
                  >
                    <b>Add to Cart!</b>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ItemDescriptionPopup;
