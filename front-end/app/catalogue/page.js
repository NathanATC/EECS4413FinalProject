"use client";
import React, { useState, useEffect } from "react";
import ItemDescriptionPopup from "../components/itemDescription";
import Item from "../models/Item";

const Catalogue = () => {
  const [itemArray, setitemArray] = useState([]);
  const [displayArray, setDisplayArray] = useState([]);
  const [itemData, setItemData] = useState({});
  const [isVisible, setIsVisible] = useState(false);
  const [filter, setFilter] = useState("");
  const [brandFilter, setBrandFilter] = useState("");

  function resetCatButton() {
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
              setBrandFilter("");
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
        sorted = [...displayArray].sort((a, b) => a.price - b.price);
        await setDisplayArray(sorted);
        console.log("sorted");
        break;
      case "nasc":
        sorted = [...displayArray].sort((a, b) => a.name.localeCompare(b.name));
        await setDisplayArray(sorted);
        console.log("sorted");
        break;
      case "desc":
        sorted = [...displayArray].sort((a, b) => b.price - a.price);
        await setDisplayArray(sorted);
        console.log("sorted");
        break;
      case "ndesc":
        sorted = [...displayArray].sort((a, b) => b.name.localeCompare(a.name));
        await setDisplayArray(sorted);
        console.log("sorted");
        break;
    }
  }

  // async function filterArray(filter, filterName) {
  //   const reqParam = { filterType: filter, filterFor: filterName };
  //   const res = await fetch("http://localhost:8080/Backend/catalogue", {
  //     method: "POST",
  //     body: JSON.stringify(reqParam),
  //   });
  //   const fetchedData = await res.json();
  //   const convert = [];
  //   fetchedData.map((item) => {
  //     convert.push(
  //       new Item(
  //         item.id,
  //         item.itemName,
  //         item.category,
  //         "",
  //         item.currentQuantity,
  //         item.price,
  //         "",
  //         item.image
  //       )
  //     );
  //   });
  //   await setitemArray(convert);
  // }

  async function filterArray(filter, catName, brand) {
    var filterFcn = null;
    if (catName && brand) {
      filterFcn = (item) => {
        return item.category == catName && item.brand == brand;
      };
    } else {
      if (catName) {
        filterFcn = (item) => {
          return item.category == catName;
        };
      }
      if (brand) {
        filterFcn = (item) => {
          return item.brand == brand;
        };
      }
    }
    if (filterFcn) {
      var tmp = itemArray.filter(filterFcn);
      setDisplayArray(tmp);
    }
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
    await setDisplayArray(convert);
  }

  const categories = ["Fruit", "Alchol", "Weat"];
  const brands = ["a", "b", "c"];

  const applyFilter = (event) => {
    console.log(event.target.value);
    setFilter(event.target.value);
  };

  const applyBrandFilter = (e) => {
    console.log(e.target.value);
    setBrandFilter(e.target.value);
  };

  useEffect(() => {
    fetchData();
  }, []);

  useEffect(() => {
    filterArray("", filter, brandFilter);
  }, [filter, brandFilter]);

  return (
    <>
      <div className="h-4"></div>
      <div className="flex flex-row justify-around">
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
      <div className="flex justify-center gap-x-4">
        <select onChange={applyFilter} value={filter}>
          <option hidden key="" value="">
            Select Category to Filter
          </option>
          {categories.map((cat) => (
            <option key={cat} value={cat}>
              {cat}
            </option>
          ))}
        </select>

        <select onChange={applyBrandFilter} value={brandFilter}>
          <option hidden key="" value="">
            Select Brand to Filter
          </option>
          {brands.map((brand) => (
            <option key={brand} value={brand}>
              {brand}
            </option>
          ))}
        </select>
        {resetCatButton()}
      </div>
      <div className="h-4"></div>
      <div className="grid grid-cols-4 gap-x-8 gap-y-4">
        {displayArray.map((item) => {
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

export default Catalogue;
