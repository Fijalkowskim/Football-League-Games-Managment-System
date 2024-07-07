import React, { useDebugValue, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DetailsPageWrapper from "../DetailsPageWrapper";
import GoalCard from "../../components/containers/cards/GoalCard";
import { fetchGoalDetails } from "../../hooks/fetchGoalDetails";
import { usePopupContext } from "../../context/PopupContext";
import { useFetchData } from "../../hooks/useFetchData";
import ScrollableNavContainer from "../../components/containers/ScrollableNavContainer";
import api from "../../api/api";
import GameCard from "../../components/containers/cards/GameCard";
import ClubCard from "../../components/containers/cards/ClubCard";
import NavCard from "../../components/containers/NavCard";

function GoalDetails() {
  const { id } = useParams();
  const { data, isPending } = useFetchData("/goals", id);
  const { logError, addMessage } = usePopupContext();

  const [club, setClub] = useState([]);
  const [matches, setMatches] = useState([]);
  const [goals, setGoals] = useState();
  const [assists, setAssists] = useState();

  const navigate = useNavigate();

  useEffect(() => {
    const loadDetails = async () => {
      if (data) {
        const { club, matches, goals, assists } = await fetchGoalDetails(
          data,
          logError
        );
        setClub(club);
        setMatches(matches);
        setGoals(goals);
        setAssists(assists);
      }
    };
    loadDetails();
  }, [data]);

  const onDelete = async () => {
    try {
      const res = await api.delete(`/goals/${data?.id}`);
      if (res) {
        addMessage("Goal deleted successfully");
        navigate("/goals");
      }
    } catch (err) {
      logError(err);
    }
  };

  return (
    <DetailsPageWrapper
      header={`Goal details`}
      isPending={isPending}
      data={data}
      editPageLink={"/goal/edit"}
      deleteMethod={onDelete}
    >
      <div className="flex flex-row items-center justify-center gap-8 bg-background-50 p-4 rounded-md shadow-md">
        <GoalCard data={data} />
      </div>

      <h1 className="text-2xl">Club</h1>
      <NavCard navigationPrefix={"club"} data={club} CardContent={ClubCard} />

      <h1 className="text-2xl">Matches</h1>
      <ScrollableNavContainer
        data={matches}
        isPending={false}
        navigationPrefix={"game"}
        CardContent={GameCard}
      />
      <h1 className="text-2xl">Goals</h1>
      <ScrollableNavContainer
        data={goals}
        isPending={false}
        navigationPrefix={"goal"}
        CardContent={GoalCard}
      />
      <h1 className="text-2xl">Assists</h1>
      <ScrollableNavContainer
        data={assists}
        isPending={false}
        navigationPrefix={"game"}
        CardContent={GoalCard}
      />
    </DetailsPageWrapper>
  );
}

export default GoalDetails;
