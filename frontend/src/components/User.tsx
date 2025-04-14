// User.tsx
import React from 'react';
import { Button, TableCell, TableRow } from '@mui/material';

interface UserProps {
    id: number;
    email: string;
    password: string;
    role: string;
    active: boolean;
    onToggleActive: () => void;
}

const User: React.FC<UserProps> = ({ id, email, password, role, active, onToggleActive }) => {
    return (
        <TableRow>
            <TableCell>{id}</TableCell>
            <TableCell>{email}</TableCell>
            <TableCell>{password}</TableCell>
            <TableCell>{role}</TableCell>
            <TableCell>{active ? 'Ano' : 'Ne'}</TableCell>
            <TableCell>
                <Button variant="contained" onClick={onToggleActive}>
                    Přepnout aktivitu
                </Button>
            </TableCell>
        </TableRow>
    );
};

export default User;