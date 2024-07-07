import React from "react";
import { useParams } from "react-router-dom";
import DetailsPageWrapper from "./DetailsPageWrapper";
import { useClub } from "../hooks/exampleDataHooks/useClub";
import ClubCard from "../components/containers/ClubCard";

function ClubDetails() {
  const { id } = useParams();
  const { data, isPending } = useClub("/club", id);
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
