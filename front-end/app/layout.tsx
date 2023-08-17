"use client"

import './globals.css'
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import NavBar from './components/navbar'
import {LoginContextProvider} from './context/loginContext';

const inter = Inter({ subsets: ['latin'] })


export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
      <LoginContextProvider>
        <NavBar/>
        {children}
      </LoginContextProvider>
      </body>
    </html>
  )
}
