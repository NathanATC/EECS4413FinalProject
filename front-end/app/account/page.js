"use client";

import { useRouter } from "next/navigation";
import { useLoginContext } from "../context/loginContext";

const AccountPage = () => {
  const context = useLoginContext();
  const router = useRouter();

  const logOut = () => {
    context.setUserContext("");
    router.push("/");
  };

  if (context.userContext.accType == "admin") {
    return (
      <>
        <button onClick={logOut}>Log Out!</button>
        <h1>Fuck You</h1>
      </>
    );
  }
  return (
    <>
      <button onClick={logOut}>Log Out!</button>
      <div>
        <h1>User ID : {context.userContext.username}</h1>
      </div>
      <div>
        <button class="rounded-full">This yo Account</button>
      </div>
    </>
  );
};

export default AccountPage;
