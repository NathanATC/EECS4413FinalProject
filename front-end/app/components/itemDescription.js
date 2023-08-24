import React, { useState } from "react";
import { useLoginContext } from "../context/loginContext";
import { ItemOrder } from "../models/ItemOrder";

const ItemDescriptionPopup = ({ item, isVisible, onClose }) => {
  const context = useLoginContext();
  const [error, setError] = useState("");
  const [qty, setQty] = useState(1);

  async function addToCart(username) {
    if (typeof qty == "number") {
      const convert = { method: "ADD", item, username: username, qty: qty };
      const body = JSON.stringify(convert);
      console.log(body);
      const res = await fetch("http://localhost:8080/Backend/Cart", {
        method: "POST",
        headers: new Headers({
          "Content-Type": "application/x-www-form-urlencoded",
        }),
        body: body,
      });
      const json = res.json();
      console.log(json);
    } else {
      alert("type number dummy");
    }
  }

  async function deleteFromCart(username) {
    const convert = { method: "CLEAR", username: username };
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

  const reset = () => {
    onClose();
    setError("");
  };

  if (!isVisible) return null;
  return (
    <>
      <div className="fixed inset-0 bg-black bg-opacity-25 backdrop-blur-sm flex justify-center items-center">
        <div className="flex flex-col w-1/3">
          <div className="flex flex-col bg-white p-2 rounded-lg gap-y-4">
            <div className="flex justify-end">
              <button className="w-8 h-8 text-lg" onClick={reset}>
                X
              </button>
            </div>
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
                  <label for="qty">Amount:</label>
                  <input
                    className="w-16"
                    type="text"
                    id="qty"
                    name="qty"
                    defaultValue="1"
                    onInput={(e) => {
                      const tmpVal = parseInt(e.target.value, 10);
                      setQty(tmpVal);
                    }}
                  />
                </div>
                <div className="flex flex-col justify-around">
                  <button
                    className="rounded-full bg-[#60a5fa] px-4 py-2"
                    onClick={() => {
                      if (context.userContext.username) {
                        addToCart(context.userContext.username);
                        onClose();
                      } else {
                        setError("Login to Add!");
                      }
                    }}
                  >
                    <b>Add to Cart!</b>
                  </button>
                  <button
                    className="rounded-full bg-[#60a5fa] px-4 py-2"
                    onClick={() => {
                      if (context.userContext.username) {
                        deleteFromCart(context.userContext.username);
                        onClose();
                      } else {
                        setError("Login to clear!");
                      }
                    }}
                  >
                    <b>Clear Cart!</b>
                  </button>
                  {error && <h3>{error}</h3>}
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
