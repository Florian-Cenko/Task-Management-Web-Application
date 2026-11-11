
const BASE_URL = "http://localhost:8080/api/tasks";

// GET: Fetch all tasks
export const getAllTasks = async () => {
    const response = await fetch(BASE_URL);

    if (!response.ok) {
        throw new Error("Failed to fetch tasks");
    }

    return response.json();
};

// POST: Create a new task
export const addTask = async (taskPayload) => {
    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(taskPayload)
    });

    if (!response.ok) {
        throw new Error("Failed to create task");
    }

    return response.json();
};

// PUT: Update an existing task
export const updateTask = async (taskId, taskPayload) => {
    const response = await fetch(`${BASE_URL}/${taskId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(taskPayload)
    });

    if (!response.ok) {
        throw new Error("Failed to update task");
    }

    return response.json();
};

// DELETE: Remove a task
export const deleteTask = async (taskId) => {
    const response = await fetch(`${BASE_URL}/${taskId}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        throw new Error("Failed to delete task");
    }

    return true;
};
