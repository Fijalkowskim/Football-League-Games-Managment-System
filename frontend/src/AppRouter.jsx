import React, { Children } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/navbar/Navbar";
import ScrollToTop from "./components/general/ScrollToTop";
import Clubs from "./pages/Clubs";
import ClubDetails from "./pages/ClubDetails";
import Players from "./pages/Players";
import Games from "./pages/Games";
import Goals from "./pages/Goals";
import CreateClub from "./pages/CreateClub";
function AppRouter() {
  return (
    <>
      <Navbar />
      <ScrollToTop />
      <Routes>
        <Route path="/clubs" element={<Clubs />} />
        <Route path="/club/:id" element={<ClubDetails />} />
        <Route path="/club/new" element={<CreateClub edit={false} />} />
        <Route path="/club/edit/:id" element={<CreateClub edit={true} />} />

        <Route path="/players" element={<Players />} />
        <Route path="/games" element={<Games />} />
        <Route path="/goals" element={<Goals />} />
        <Route path="*" element={<Navigate to="/clubs" />} />
      </Routes>
    </>
  );
}

export default AppRouter;
