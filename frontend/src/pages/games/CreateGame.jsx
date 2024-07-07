import React, { useEffect, useState } from "react";
import CreatePageWrapper from "../CreatePageWrapper";
import CustomButton from "../../components/general/CustomButton";
import { usePopupContext } from "../../context/PopupContext";
import api from "../../api/api";
import { useNavigate, useParams } from "react-router-dom";
import MultiselectContainer from "../../components/containers/MultiselectContainer";
import PlayerCard from "../../components/containers/cards/PlayerCard";
import SelectContainer from "../../components/containers/SelectContainer";
import ClubCard from "../../components/containers/cards/ClubCard";

function CreateGame({ edit }) {
  const { id } = useParams();

  const [location, setLocation] = useState("");
  const [date, setDate] = useState(new Date());
  const [homeClub, setHomeClub] = useState();
  const [awayClub, setAwayClub] = useState();
  const [homePlayers, setHomePlayers] = useState([]);
  const [awayPlayers, setAwayPlayers] = useState([]);

  const { logError, addMessage } = usePopupContext();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await api.get(`/matches/${id}`);
        const game = res.data;
        setLocation(game.location);
        setDate(game.date);
        setHomeClub(game.homeClubId);
        setAwayClub(game.awayClubId);
      } catch (err) {
        logError(err);
      }
    };
    if (edit && id) {
      fetchData();
    }
  }, [edit, id]);

  const onSubmit = async (e) => {
    e.preventDefault();
    const body = {
      location: location,
      date: date,
      players: [...homePlayers, ...awayPlayers],
      homeClubId: homeClub,
      awayClubId: awayClub,
    };
    console.log(body);
    try {
      const res =
        edit && id
          ? await api.put(`/matches/${id}`, body)
          : await api.post("/matches", body);
      if (res) {
        addMessage(`Game ${edit ? "edited" : "created"} successfully`);
        navigate("/games");
      }
    } catch (err) {
      logError(err);
    }
  };
  return (
    <CreatePageWrapper header={edit ? "Edit Game" : "New Game"}>
      <form
        onSubmit={onSubmit}
        className="flex flex-col gap-2 items-center justify-center"
      >
        <label>Location:</label>
        <input
          type="text"
          required
          value={location}
          className="p-2 w-screen max-w-3xl"
          onChange={(e) => {
            setLocation(e.target.value);
          }}
        />
        <label>Date of creation:</label>
        <input
          type="date"
          required
          value={date}
          className="p-2 w-screen max-w-3xl"
          onChange={(e) => {
            setDate(e.target.value);
          }}
        />

        <label className="text-2xl">Home club:</label>
        <div className=" max-h-[30rem] w-screen max-w-4xl">
          <SelectContainer
            apiUrl={`/clubs`}
            setMethod={setHomeClub}
            CardContent={ClubCard}
            prohibitedId={awayClub}
          />
        </div>

        <label className="text-2xl">Away club:</label>
        <div className=" max-h-[30rem] w-screen max-w-4xl">
          <SelectContainer
            apiUrl={`/clubs`}
            setMethod={setAwayClub}
            CardContent={ClubCard}
            prohibitedId={homeClub}
          />
        </div>

        {homeClub && awayClub && (
          <>
            <label className="text-2xl">Home players:</label>
            <div className=" max-h-[30rem] w-screen max-w-4xl">
              <MultiselectContainer
                apiUrl={`/clubs/${homeClub.id}/players`}
                setMethod={setHomePlayers}
                maxEntries={11}
                CardContent={PlayerCard}
              />
            </div>
            <label className="text-2xl">Away players:</label>
            <div className=" max-h-[30rem] w-screen max-w-4xl">
              <MultiselectContainer
                apiUrl={`/clubs/${awayClub.id}/players`}
                setMethod={setAwayPlayers}
                maxEntries={11}
                CardContent={PlayerCard}
              />
            </div>
          </>
        )}
        <CustomButton>Create</CustomButton>
      </form>
    </CreatePageWrapper>
  );
}

export default CreateGame;
