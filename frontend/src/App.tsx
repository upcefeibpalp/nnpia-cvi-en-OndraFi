import './App.css';
import {Navigate, Route, Routes} from "react-router-dom";
import UserList from "./pages/UserList.tsx";
import Register from "./pages/Register.tsx";
import {useSelector} from "react-redux";
import {RootState} from "./store.ts";
import Login from "./pages/Login.tsx";

const App = () => {
    const token = useSelector((state: RootState) => state.auth.token);

    return (
        <Routes>
            <Route path="/" element={<Navigate to="/users" />} />
            <Route path="/users" element={<UserList />} />
            <Route path="/register" element={<Register />} />
            <Route
                path="/login"
                element={!token ? <Login /> : <Navigate to="/users" />}
            />
        </Routes>
    );
};


export default App;
