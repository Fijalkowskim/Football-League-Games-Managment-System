import api from "../api/api";
import { usePopupContext } from "../context/PopupContext";

export const useClubDetails = async (clubData) => {
  if (!clubData) return;
  const { logError } = usePopupContext();
  var _players = [];
  var _homeMatches = [];
  var _awayMatches = [];
  try {
    const players = await api.get(clubData.players);
    if (players) _players = players;
    const homeMatches = await api.get(clubData.homeMatches);
    if (homeMatches) _homeMatches = homeMatches;
    const awayMatches = await api.get(clubData.awayMatches);
    if (awayMatches) _awayMatches = awayMatches;
  } catch (err) {
    logError(err);
  }
  return { _players, _homeMatches, _awayMatches };
};
