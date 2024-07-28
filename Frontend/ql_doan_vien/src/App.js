import React, { useEffect, useState } from "react";
import './App.css';
import 'react-toastify/dist/ReactToastify.css';
import { Route, Routes } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import QLDV from "./components/QLDV/QLDV";
import Login from "./components/Login/Login";


function App() {
 
  return (
   <>
    <div className="App">
         <Routes>
            <Route path="/" element={ <Login/>}/>
            <Route path="/qldv/*" element={<QLDV/>}/>
         </Routes>
    </div>

    <ToastContainer/>
   </>
  );
}

export default App;
