import { useState, useEffect } from "react";
import { exampleClubs } from "../../exampleData/exampleData";
export const useClub = (apiUrl, id) => {
  const [data, setData] = useState(exampleClubs[parseInt(id) - 1]);
  const [isPending, setIsPending] = useState(false);
  return { data, isPending };
};
