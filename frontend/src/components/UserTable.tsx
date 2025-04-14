// UserTable.tsx
import React from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import User from './User';

interface User {
    email: string,
    id: number,
    role: string,
    active: boolean
}

interface UserTableProps {
    users: User[];
    toggleUserActive: (id: number, active: boolean) => void;
}

const UserTable: React.FC<UserTableProps> = ({ users, toggleUserActive }) => {
    return (
        <TableContainer component={Paper} sx={{ maxWidth: 1000, margin: 'auto', mt: 4 }}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Email</TableCell>
                        <TableCell>Password</TableCell>
                        <TableCell>Role</TableCell>
                        <TableCell>Active</TableCell>
                        <TableCell>Akce</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {users.map(user => (
                        <User
                            key={user.id}
                            {...user}
                            onToggleActive={() => toggleUserActive(user.id,!user.active)}
                        />
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default UserTable;
