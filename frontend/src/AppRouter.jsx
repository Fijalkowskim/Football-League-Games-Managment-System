import React, { Children } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/navbar/Navbar";
import ScrollToTop from "./components/general/ScrollToTop";
import Clubs from "./pages/clubs/Clubs";
import ClubDetails from "./pages/clubs/ClubDetails";
import Players from "./pages/Players";
import Games from "./pages/games/Games";
import Goals from "./pages/Goals";
import CreateClub from "./pages/clubs/CreateClub";
import GameDetails from "./pages/games/GameDetails";
import CreateGame from "./pages/games/CreateGame";
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
        <Route path="/game/:id" element={<GameDetails />} />
        <Route path="/game/new" element={<CreateGame edit={false} />} />
        <Route path="/game/edit/:id" element={<CreateGame edit={true} />} />

        <Route path="/goals" element={<Goals />} />
        <Route path="*" element={<Navigate to="/clubs" />} />
      </Routes>
    </>
  );
}

export default AppRouter;
