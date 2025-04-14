import './App.css';
import {Navigate, Route, Routes} from "react-router-dom";
import UserList from "./pages/UserList.tsx";
import Register from "./pages/Register.tsx";

const App = () => {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/users" />} />
            <Route path="/users" element={<UserList />} />
            <Route path="/register" element={<Register />} />
        </Routes>
    );
};


export default App;
