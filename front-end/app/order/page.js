"use client";
import { useLoginContext } from "../context/loginContext";







const Order = () => {
  const context = useLoginContext();


  async function placeOrder(username) {
    const convert = { method: "POST", username: username};
    const body = JSON.stringify(convert);
    const res = await fetch("http://localhost:8080/Backend/CheckoutServlet", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/x-www-form-urlencoded",
      }),
      body: body,
    });
  }





  return (
    <>
      <h1>Finalize Order!</h1>

      <button
      onClick={async () =>{
        await placeOrder(context.userContext.username);
      }}      
      > <b>place order</b> </button>
      
    </>
  );
};

export default Order;
