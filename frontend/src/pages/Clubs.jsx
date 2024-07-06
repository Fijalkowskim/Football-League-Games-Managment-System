import React from "react";
import PageWrapper from "./PageWrapper";
import NavContainer from "../components/containers/NavContainer";
import { useClubs } from "../hooks/exampleDataHooks/useClubs";
import ClubCard from "../components/containers/ClubCard";

function Clubs() {
  const { data, isPending } = useClubs("/clubs");
  return (
    <PageWrapper header={"List of clubs"}>
      <NavContainer
        data={data}
        isLoading={isPending}
        navigationPrefix={"/club"}
        CardContent={ClubCard}
      />
    </PageWrapper>
  );
}

export default Clubs;
