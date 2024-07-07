import React, { useDebugValue, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import DetailsPageWrapper from "./DetailsPageWrapper";
import { useClub } from "../hooks/exampleDataHooks/useClub";
import ClubCard from "../components/containers/ClubCard";
import { fetchClubDetails } from "../hooks/fetchClubDetails";
import { usePopupContext } from "../context/PopupContext";
import { useFetchData } from "../hooks/useFetchData";
import NavContainer from "../components/containers/NavContainer";
import ScrollableNavContainer from "../components/containers/ScrollableNavContainer";

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
    loadDetails();
  }, [data]);
  return (
    <DetailsPageWrapper
      header={`Club details`}
      isPending={isPending}
      data={data}
      editPageLink={"/club/edit"}
    >
      <div className="flex flex-row items-center justify-center gap-8 bg-primary-100 p-4 rounded-md shadow-md">
        <ClubCard data={data} />
      </div>
      <h1 className="text-2xl">Players</h1>
      <ScrollableNavContainer
        data={players}
        isPending={false}
        navigationPrefix={"player"}
      />
      <h1 className="text-2xl">Home Matches</h1>
      <ScrollableNavContainer
        data={homeMatches}
        isPending={false}
        navigationPrefix={"match"}
      />
      <h1 className="text-2xl">Away Matches</h1>
      <ScrollableNavContainer
        data={awayMatches}
        isPending={false}
        navigationPrefix={"match"}
      />
    </DetailsPageWrapper>
  );
}

export default ClubDetails;
