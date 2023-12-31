import { React, useState } from "react";
import { useLoginContext } from "../context/loginContext";
import Link from "next/link";

const SignInPopup = ({ isVisible, onClose }) => {
  const [usernameField, setUsernameField] = useState("1");
  const [passwordField, setPasswordField] = useState("2");
  const context = useLoginContext();

  async function login(username, password) {
    // if (localStorage.getItem(user)) {
    //   context.setUserContext(localStorage.getItem(user));
    // }
    const user = username;
    const pass = password;
    const body = `{username:${user},password:${pass}}`;
    setUsernameField("1");
    setPasswordField("2");
    console.log(body);
    const res = await fetch("http://localhost:8080/Backend/LoginServlet", {
      method: "POST",
      headers: new Headers({
        "Content-Type": "application/x-www-form-urlencoded",
      }),
      body: body,
    });
    const data = await res.json();
    if (data["username"] != null) {
      console.log(data);

      context.setUserContext(data);
    } else {
      alert("Inncorrect username or password!");
    }
  }

  if (!isVisible) return null;
  return (
    <div className="fixed inset-0 bg-black bg-opacity-25 backdrop-blur-sm flex justify-center items-center">
      <div className="flex flex-col w-1/2">
        <button
          className="text-white text-xl place-self-end"
          onClick={() => onClose()}
        >
          X
        </button>
        <div className="flex flex-col bg-white p-2 rounded h-64 gap-y-4">
          <div className="flex justify-center">Sign In</div>
          <form
            className="flex flex-col gap-y-4"
            onSubmit={() => {
              login(usernameField, passwordField);
              onClose();
            }}
          >
            <div>
              <label for="username">Username:</label>
              <input
                type="text"
                id="username"
                name="username"
                onInput={(e) => setUsernameField(e.target.value)}
              />
            </div>
            <div>
              <label for="password">Password:</label>
              <input
                type="password"
                id="password"
                name="password"
                onInput={(e) => setPasswordField(e.target.value)}
              />
            </div>
            <div className="flex place-content-around">
              <button type="submit">Login</button>
              <input type="reset" />
              <Link href="/signup" passHref>
                <button>Create Account</button>
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignInPopup;
