import React, { useState } from "react";
import { useFetchArrayData } from "../../hooks/useFetchArrayData";
import LoadingBar from "../general/LoadingBar";
import CustomButton from "../general/CustomButton";

function MultiselectContainer({
  apiUrl,
  setMethod,
  CardContent,
  maxEntries = Infinity,
  header,
}) {
  const { data, isPending } = useFetchArrayData(apiUrl);
  const [selected, setSelected] = useState();
  const onCardClick = (cardData) => {
    setMethod((prev) => {
      if (prev.find((e) => e === cardData.id)) {
        setSelected(prev.filter((id) => id !== cardData.id));
        return prev.filter((id) => id !== cardData.id);
      } else if (prev.length < maxEntries) {
        setSelected([...prev, cardData.id]);
        return [...prev, cardData.id];
      }
    });
  };
  return (
    <div className="w-full h-full flex flex-col items-center justify-center gap-2 p-2 px-4 bg-primary-900/30 rounded-md">
      {isPending ? (
        <LoadingBar />
      ) : data === undefined || data.length <= 0 ? (
        <p>No data yet</p>
      ) : (
        <>
          <h1 className="text-xl">{header}</h1>
          {data.map((entry) => (
            <CustomButton
              type="button"
              onClick={() => onCardClick(entry)}
              className={`w-full gap-6 ${
                selected.find((e) => e === entry.id)
                  ? "border-4 border-red-500"
                  : ""
              }`}
            >
              <CardContent data={entry} />
            </CustomButton>
          ))}
        </>
      )}
    </div>
  );
}

export default MultiselectContainer;
