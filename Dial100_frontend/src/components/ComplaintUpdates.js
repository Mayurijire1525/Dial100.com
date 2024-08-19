import React, { useState } from 'react';

function ComplaintUpdates({ complaint }) {
  const [status, setStatus] = useState(complaint.status);

  const handleStatusChange = (e) => {
    setStatus(e.target.value);
    // Handle status update logic
    console.log('Complaint Status Updated:', status);
  };

  return (
    <div className="complaint-updates">
      <h2>Update Complaint Status</h2>
      <select value={status} onChange={handleStatusChange}>
        <option value="Pending">Pending</option>
        <option value="In Progress">In Progress</option>
        <option value="Resolved">Resolved</option>
      </select>
    </div>
  );
}

export default ComplaintUpdates;
