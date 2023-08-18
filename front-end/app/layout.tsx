"use client";

import "./globals.css";
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import NavBar from "./components/navbar";
import { LoginContextProvider } from "./context/loginContext";
import { CartContextProvider } from "./context/cartContext";

const inter = Inter({ subsets: ["latin"] });

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <LoginContextProvider>
          <CartContextProvider>
            <NavBar />
            {children}
          </CartContextProvider>
        </LoginContextProvider>
      </body>
    </html>
  );
}
