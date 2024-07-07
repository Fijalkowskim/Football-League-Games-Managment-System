import React, { useDebugValue, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import DetailsPageWrapper from "./DetailsPageWrapper";
import { useClub } from "../hooks/exampleDataHooks/useClub";
import ClubCard from "../components/containers/ClubCard";
import { fetchClubDetails } from "../hooks/fetchClubDetails";
import { usePopupContext } from "../context/PopupContext";
import { useFetchData } from "../hooks/useFetchData";

function ClubDetails() {
  const { id } = useParams();
  const { data, isPending } = useFetchData("/clubs", id);
  const { logError } = usePopupContext();

  const [players, setPlayers] = useState([]);
  const [homeMatches, setHomeMatches] = useState([]);
  const [awayMatches, setAwayMatches] = useState([]);

  useEffect(() => {
    const loadDetails = async () => {
      if (data) {
        const { players, homeMatches, awayMatches } = await fetchClubDetails(
          data,
          logError
        );
        setPlayers(players);
        setHomeMatches(homeMatches);
        setAwayMatches(awayMatches);
      }
    };
    //loadDetails();
  }, [data]);
  return (
    <DetailsPageWrapper
      header={`Club details`}
      isPending={isPending}
      data={data}
    >
      <div className="flex flex-row items-center justify-center gap-8">
        <ClubCard data={data} />
      </div>
    </DetailsPageWrapper>
  );
}

export default ClubDetails;
