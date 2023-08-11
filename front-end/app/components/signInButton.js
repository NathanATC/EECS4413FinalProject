import React from "react";
import {useLoginContext} from '../context/loginContext';
import Link from 'next/link';

export default function SignInButton({onClick}) {
    const context = useLoginContext();
    if(context.userContext.id != null) {
        return (
            <Link className ="rounded-full bg-[#60a5fa] " href="/account" passHref>
                <button className="px-4 py-3">My Account</button>
            </Link> 
        );
    } else {
        return (
            <button className="rounded-full bg-[#60a5fa] px-8 py-1" onClick={()=>onClick()}>Sign In</button>
        );
    }
}