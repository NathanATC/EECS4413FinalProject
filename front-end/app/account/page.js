"use client";
import React, { useState } from "react";
import { useRouter } from "next/navigation";
import { useLoginContext } from "../context/loginContext";

const AccountPage = () => {
  const context = useLoginContext();
  const router = useRouter();

  const [orders, setOrders] = useState([]);

  const logOut = () => {
    context.setUserContext("");
    localStorage.clear();
    router.push("/");
  };

  async function fetchOrders() {
    const res = await fetch("http://localhost:8080/Backend/SalesHistory");
    const fetchedData = await res.json();
    console.log(fetchedData);
    setOrders(fetchedData);
  }
  console.log(context.userContext.permissions);
  if (context.userContext.permissions == "Admin") {
    return (
      <>
        <div className="flex justify-around">
          <div className="flex flex-col">
            <button onClick={logOut}>Log Out!</button>
            <h1>User ID : {context.userContext.username}</h1>
            <h1>Email : {context.userContext.email}</h1>
            <h1>Account Type : {context.userContext.permissions}</h1>
          </div>
          <div className="flex flex-col">
            <h1>Manage Orders!</h1>
            <button onClick={() => fetchOrders()}>Load Current Orders</button>
            {orders
              ? orders.map((order) => (
                  <div className="flex flex-row gap-x-4 bg-[#bfdbfe] border-2 border-[#93c5fd]">
                    {order.orderID}
                    <div>
                      {order.items.map((item) => (
                        <>
                          <div className="flex flex-row gap-x-4">
                            <p>{item.itemName}</p>
                            <p>x{order.orderQuanties[item.id]}</p>
                          </div>
                        </>
                      ))}
                    </div>
                    {order.username}
                  </div>
                ))
              : null}
          </div>
        </div>
      </>
    );
  }
  return (
    <>
      <button onClick={logOut}>Log Out!</button>
      <div>
        <h1>User ID : {context.userContext.username}</h1>
        <h1>Email : {context.userContext.email}</h1>
        <h1>Account Type : {context.userContext.permissions}</h1>
      </div>
    </>
  );
};

export default AccountPage;
