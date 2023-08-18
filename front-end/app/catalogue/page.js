"use client";
import React, { useState, useEffect } from "react";
import ItemDescriptionPopup from "../components/itemDescription";
import Item from "../models/Item";

const catalogue = () => {
  const [itemArray, setitemArray] = useState([]);
  const [itemData, setItemData] = useState({});
  const [isVisible, setIsVisible] = useState(false);
  const [filter, setFilter] = useState("");

  function resetCatButton(filter) {
    if (filter == "") {
      return null;
    }
    return (
      <>
        <div className="rounded-full w-48 h-12 bg-[#60a5fa] flex justify-center">
          <button
            className="px-4 py-2"
            onClick={() => {
              fetchData();
              setFilter("");
            }}
          >
            Reset Catalogue
          </button>
        </div>
        <div className="h-4"></div>
      </>
    );
  }

  async function sortArray(cmd) {
    var sorted = [];
    switch (cmd) {
      case "asc":
        sorted = [...itemArray].sort((a, b) => a.price - b.price);
        await setitemArray(sorted);
        console.log("sorted");
        break;
      case "nasc":
        sorted = [...itemArray].sort((a, b) => a.name.localeCompare(b.name));
        await setitemArray(sorted);
        console.log("sorted");
        break;
      case "desc":
        sorted = [...itemArray].sort((a, b) => b.price - a.price);
        await setitemArray(sorted);
        console.log("sorted");
        break;
      case "ndesc":
        sorted = [...itemArray].sort((a, b) => b.name.localeCompare(a.name));
        await setitemArray(sorted);
        console.log("sorted");
        break;
    }
  }

  async function filterArray(filter, filterName) {
    const reqParam = { filterType: filter, filterFor: filterName };
    const res = await fetch("http://localhost:8080/Backend/catalogue", {
      method: "POST",
      body: JSON.stringify(reqParam),
    });
    const fetchedData = await res.json();
    const convert = [];
    fetchedData.map((item) => {
      convert.push(
        new Item(
          item.id,
          item.itemName,
          item.category,
          "",
          item.currentQuantity,
          item.price,
          "",
          item.image
        )
      );
    });
    await setitemArray(convert);
  }

  async function fetchData() {
    const res = await fetch("http://localhost:8080/Backend/catalogue");
    const fetchedData = await res.json();
    const convert = [];
    fetchedData.map((item) => {
      convert.push(
        new Item(
          item.id,
          item.itemName,
          item.category,
          "",
          item.currentQuantity,
          item.price,
          "",
          item.image
        )
      );
    });
    await setitemArray(convert);
  }

  const applyFilter = (event) => {
    console.log(event.target.value);
    filterArray("cat", event.target.value);
    setFilter(event.target.value);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <div className="h-4"></div>
      <div className="flex flex-row">
        <div className="rounded-full w-48 h-12 bg-[#60a5fa] text-sm flex justify-center">
          <button onClick={() => sortArray("asc")}>Sort Ascending Price</button>
        </div>
        <div className="rounded-full w-48 h-12 bg-[#60a5fa] text-sm flex justify-center">
          <button onClick={() => sortArray("desc")}>
            Sort Descending Price
          </button>
        </div>
        <div className="rounded-full w-48 h-12 bg-[#60a5fa] text-sm flex justify-center">
          <button onClick={() => sortArray("nasc")}>Sort Ascending Name</button>
        </div>
        <div className="rounded-full w-48 h-12 bg-[#60a5fa] text-sm flex justify-center">
          <button onClick={() => sortArray("ndesc")}>
            Sort Descending Name
          </button>
        </div>
      </div>
      <div className="h-4"></div>
      <select onChange={applyFilter} value={filter}>
        <option key="none" value="">
          Select Category to Filter
        </option>
        <option key="Fruit" value="Fruit">
          Fruit
        </option>
        <option key="Alchol" value="Alchol">
          Alcohol
        </option>
        <option key="Weat" value="Weat">
          Grain
        </option>
      </select>
      <div className="h-4"></div>
      {resetCatButton(filter)}
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

export default catalogue;
