import React from "react";
import NavContainer from "./NavContainer";
import { cn } from "../../helpers/helpers";

function ScrollableNavContainer({
  data,
  isLoading,
  navigationPrefix,
  CardContent,
  className,
}) {
  return (
    <div className={cn("h-fit max-h-[30rem] overflow-y-scroll p-2", className)}>
      <NavContainer
        data={data}
        isLoading={isLoading}
        navigationPrefix={navigationPrefix}
        CardContent={CardContent}
      />
    </div>
  );
}

export default ScrollableNavContainer;
