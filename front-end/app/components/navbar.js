import React, { useContext, useState, useEffect } from "react";
import {LoginContext, useLoginContext} from '../context/loginContext';
import Link from "next/link";
import SignInPopup from "./signInPopUp";
import SignInButton from "./signInButton";

export default function NavBar() {
  const [showPopup, setShowPopup] = useState(false);
  const user = useLoginContext();
  const firstName = user.userContext.firstName
  var message = "";
  if(firstName != null){
    message = "Hello, " + firstName 
  }

  useEffect(() => {
    if (localStorage.getItem("user")) {
      const acc = JSON.parse(localStorage.getItem("user"));
      user.setUserContext(acc);
    }
  }, []);

  return (
    <>  
      <div className="bg-[#bfdbfe] border-2 border-[#93c5fd]">
      {message}
        <div className="h-4"></div>
        <div className="flex justify-center">
          <div className="relative h-16 w-16">
            <Link href="/" passHref>
              <div className="absolute rounded-full bg-[#f8fafc] w-16 h-16 border-2 border-[#020617]"></div>
              <div className="absolute rounded-full bg-[#422006] w-8 h-8 top-4 left-2"></div>
              <div className="absolute rounded-full bg-[#020617] w-4 h-4 top-6 left-3"></div>
            </Link>
          </div>
        </div>
        <div className="h-4"></div>
        <div className="flex justify-around">
          <Link
            className="rounded-full bg-[#60a5fa]"
            href="/catalogue"
            passHref
          >
            <button className="px-4 py-3">Catalogue</button>
          </Link>
          <SignInButton
            onClick={() => {
              // const obj = { username: "jimmy123" };
              // user.setUserContext(obj);
              // localStorage.setItem("user", JSON.stringify(obj));

              setShowPopup(true);

            }}
          ></SignInButton>
          <Link href="/shoppingcart">
            <button className="rounded-full bg-[#60a5fa] px-4 py-2">
              <svg
                className="h-8 w-8 fill-current text-black-500"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 36 36"
              >
                <path
                  id="Selection"
                  d="M 0.00,8.00
           C 5.06,6.83 7.27,6.58 9.00,12.00
             9.00,12.00 26.00,12.00 26.00,12.00
             27.55,12.03 30.12,11.96 31.36,13.02
             33.69,15.00 31.67,24.45 26.89,25.40
             26.89,25.40 14.04,24.85 14.04,24.85
             5.98,23.53 10.15,14.22 1.00,12.00
             1.00,12.00 0.00,8.00 0.00,8.00 Z
           M 12.60,28.15
           C 16.01,27.69 15.36,30.21 14.40,30.85
             13.45,31.49 10.87,31.12 12.60,28.15 Z
           M 26.60,28.15
           C 30.01,27.69 29.36,30.21 28.40,30.85
             27.45,31.49 24.87,31.12 26.60,28.15 Z"
                />
              </svg>
            </button>
          </Link>
        </div>
        <div className="h-4"></div>
      </div>
      <SignInPopup isVisible={showPopup} onClose={() => setShowPopup(false)} />
    </>
  );
}
