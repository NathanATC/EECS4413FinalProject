"use client";

import { useLoginContext } from "../context/loginContext";

const AccountPage = () => {
    const context = useLoginContext();
    if(context.userContext.accType == "admin"){
        return(
            <>
                <h1>Fuck You</h1>
            </>
        );
    }
    return (
    <>
    <div>
        <h1>User ID : {context.userContext.id}</h1>
    </div>
    <div>
    <button class="rounded-full">This yo Account</button>
    </div>
    </>
    );
}
 
export default AccountPage;