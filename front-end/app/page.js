"use client";
import React, { useState, useEffect } from "react";
import ItemDescriptionPopup from "./components/itemDescription";
import Item from "./models/Item";

const Home = () => {
  const [itemArray, setitemArray] = useState([]);
  const [itemData, setItemData] = useState({});
  const [isVisible, setIsVisible] = useState(false);

  async function fetchData() {
    const res = await fetch("http://localhost:8080/Backend/HotProducts");
    const fetchedData = await res.json();
    const convert = [];
    fetchedData.map((item) => {
      convert.push(
        new Item(
          item.id,
          item.itemName,
          item.category,
          item.discription,
          item.currentQuantity,
          item.price,
          "",
          item.image,
          item.brand
        )
      );
    });
    await setitemArray(convert);
  }

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <div className="flex justify-center">
        <h1>Welcome!</h1>
      </div>
      <div className="flex justify-center">
        <h2>Hot Products!</h2>
      </div>
      <div className="grid grid-cols-4 gap-x-8 gap-y-4">
        {itemArray.map((item) => {
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
                <div className="flex justify-center">
                  <button
                    onClick={() => {
                      setItemData(item);
                      setIsVisible(true);
                    }}
                  >
                    More Info!
                  </button>
                </div>
              </div>
            </>
          );
        })}
      </div>
      <ItemDescriptionPopup
        item={itemData}
        isVisible={isVisible}
        onClose={() => setIsVisible(false)}
      />
    </>
  );
};

export default Home;
