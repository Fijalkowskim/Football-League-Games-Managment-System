import React, { useState } from "react";
import CreatePageWrapper from "./CreatePageWrapper";
import CustomButton from "../components/general/CustomButton";
import { usePopupContext } from "../context/PopupContext";
import api from "../api/api";

function CreateClub() {
  const [name, setName] = useState("");
  const [location, setLocation] = useState("");
  const [dateOfCreation, setDateOfCreation] = useState(new Date());
  const { logError, addMessage } = usePopupContext();
  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/clubs", { name, location, dateOfCreation });
      if (res) {
        addMessage("Club created successfully");
        setName("");
        setLocation("");
        setDateOfCreation(new Date());
      }
    } catch (err) {
      logError(err);
    }
  };
  return (
    <CreatePageWrapper header={"New club"}>
      <form
        onSubmit={onSubmit}
        className="flex flex-col gap-2 items-center justify-center"
      >
        <label>Name:</label>
        <input
          type="text"
          required
          value={name}
          className="p-2 w-screen max-w-3xl"
          onChange={(e) => {
            setName(e.target.value);
          }}
        />
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
          value={dateOfCreation}
          className="p-2 w-screen max-w-3xl"
          onChange={(e) => {
            setDateOfCreation(e.target.value);
          }}
        />
        <CustomButton>Create</CustomButton>
      </form>
    </CreatePageWrapper>
  );
}

export default CreateClub;
