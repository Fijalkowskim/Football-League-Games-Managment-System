import React from "react";
import { NavLink } from "react-router-dom";

function NavCard({ navigationPrefix, data }) {
  return <NavLink to={`/${navigationPrefix}/${data?.id}`}>{}</NavLink>;
}

export default NavCard;
