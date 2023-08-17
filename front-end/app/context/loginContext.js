"use client";

import React, {createContext, useContext, useState} from 'react';

export const loginContext = createContext();

export const LoginContextProvider = ({children}) => {
    const [userContext, setUserContext] = useState({});
    console.log("context :" + userContext);
    return (
        <loginContext.Provider value={{userContext,setUserContext}}>
        {children}
        </loginContext.Provider>
    );
};

export const useLoginContext = () => useContext(loginContext);