import React, { Children } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/navbar/Navbar";
import ScrollToTop from "./components/general/ScrollToTop";
import Clubs from "./pages/Clubs";
function AppRouter() {
  return (
    <>
      <Navbar />
      <ScrollToTop />
      <Routes>
        <Route path="/clubs" element={<Clubs />} />
        <Route path="/players" element={<Clubs />} />
        <Route path="/games" element={<Clubs />} />
        <Route path="/goals" element={<Clubs />} />
        <Route path="*" element={<Navigate to="/clubs" />} />
      </Routes>
    </>
  );
}

export default AppRouter;
