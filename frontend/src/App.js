import AppRouter from "./AppRouter";
import PopupController from "./components/popup/PopupController";
function App() {
  return (
    <div className="overflow-x-hidden relative">
      <PopupController />
      <AppRouter />
    </div>
  );
}

export default App;
