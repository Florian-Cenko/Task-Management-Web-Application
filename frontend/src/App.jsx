
import { useState, useEffect } from "react";

import TaskList from "./components/TaskList";
import TaskForm from "./components/TaskForm";
import TaskEditForm from "./components/TaskEditForm";
import TaskFilter from "./components/TaskFilter";
import TaskModal from "./components/TaskModal";

import {
    getAllTasks,
    addTask,
    updateTask,
    deleteTask
} from "./services/taskService";

export default function App() {
    const [tasks, setTasks] = useState([]);
    const [searchTitle, setSearchTitle] = useState("");
    const [filterStatus, setFilterStatus] = useState("");
    const [showForm, setShowForm] = useState(false);
    const [editingTask, setEditingTask] = useState(null);
    const [loading, setLoading] = useState(true);

    // Load tasks from backend
    const loadTasks = async () => {
        try {
            setLoading(true);
            const data = await getAllTasks();
            setTasks(data);
        } catch (error) {
            console.error("Failed to load tasks:", error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadTasks();
    }, []);

    // Add task
    const handleAddTask = async (taskData) => {
        try {
            const newTask = await addTask(taskData);
            setTasks(prev => [...prev, newTask]);
            setShowForm(false);
        } catch (error) {
            console.error("Failed to add task:", error);
            alert("Failed to add task.");
        }
    };

    // Update task
    const handleUpdateTask = async (id, taskData) => {
        try {
            const updatedTask = await updateTask(id, taskData);

            setTasks(prev =>
                prev.map(task =>
                    task.id === id ? updatedTask : task
                )
            );

            setEditingTask(null);
        } catch (error) {
            console.error("Failed to update task:", error);
            alert("Failed to update task.");
        }
    };

    // Update task status
    const handleUpdateStatus = (id, newStatus) => {
        const taskToUpdate = tasks.find(task => task.id === id);

        if (!taskToUpdate) return;

        const updatedPayload = {
            ...taskToUpdate,
            taskStatusEnum: newStatus
        };

        handleUpdateTask(id, updatedPayload);
    };

    // Open edit modal
    const handleEditTask = (task) => {
        setEditingTask(task);
    };

    // Save edited task
    const handleSaveEditedTask = (updatedTask) => {
        handleUpdateTask(updatedTask.id, updatedTask);
    };

    // Delete task
    const handleDeleteTask = async (id) => {
        try {
            await deleteTask(id);

            setTasks(prev =>
                prev.filter(task => task.id !== id)
            );
        } catch (error) {
            console.error("Failed to delete task:", error);
            alert("Failed to delete task.");
        }
    };

    // Filter tasks
    const filteredTasks = tasks.filter(task => {
        const matchesTitle = (task.title || "")
            .toLowerCase()
            .includes(searchTitle.toLowerCase());

        const matchesStatus =
            filterStatus === "" ||
            task.taskStatusEnum === filterStatus;

        return matchesTitle && matchesStatus;
    });

    return (
        <div className="min-h-screen bg-gray-100">
            <main className="max-w-6xl mx-auto px-6 py-10">

                <header className="flex flex-col sm:flex-row justify-between items-center gap-4 mb-8">
                    <div>
                        <h1 className="text-3xl font-bold text-gray-800">
                            Task Management
                        </h1>

                        <p className="text-gray-500 mt-2">
                            Organize your work and stay productive.
                        </p>
                    </div>

                    <button
                        onClick={() => setShowForm(true)}
                        className="bg-blue-600 text-white px-5 py-3 rounded-xl hover:bg-blue-700 transition"
                    >
                        + Add New Task
                    </button>
                </header>

                {/* Add Task Modal */}
                <TaskModal
                    isOpen={showForm}
                    onClose={() => setShowForm(false)}
                >
                    <TaskForm onTaskAdded={handleAddTask} />
                </TaskModal>

                {/* Edit Task Modal */}
                <TaskModal
                    isOpen={editingTask !== null}
                    onClose={() => setEditingTask(null)}
                >
                    {editingTask && (
                        <TaskEditForm
                            task={editingTask}
                            onTaskUpdated={handleSaveEditedTask}
                            onCancel={() => setEditingTask(null)}
                        />
                    )}
                </TaskModal>

                <TaskFilter
                    searchTitle={searchTitle}
                    onSearchChange={setSearchTitle}
                    filterStatus={filterStatus}
                    onStatusChange={setFilterStatus}
                />

                {/* Task Statistics */}
                <section className="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-6">

                    <div className="bg-white p-5 rounded-xl shadow-sm">
                        <p className="text-sm text-gray-500">Total Tasks</p>
                        <h2 className="text-2xl font-bold mt-1">
                            {tasks.length}
                        </h2>
                    </div>

                    <div className="bg-white p-5 rounded-xl shadow-sm">
                        <p className="text-sm text-gray-500">In Progress</p>
                        <h2 className="text-2xl font-bold text-yellow-600 mt-1">
                            {tasks.filter(t =>
                                t.taskStatusEnum === "IN_PROGRESS"
                            ).length}
                        </h2>
                    </div>

                    <div className="bg-white p-5 rounded-xl shadow-sm">
                        <p className="text-sm text-gray-500">Completed</p>
                        <h2 className="text-2xl font-bold text-green-600 mt-1">
                            {tasks.filter(t =>
                                t.taskStatusEnum === "COMPLETED"
                            ).length}
                        </h2>
                    </div>

                </section>

                {loading ? (
                    <p className="text-center py-10 text-gray-500">
                        Loading tasks...
                    </p>
                ) : (
                    <TaskList
                        tasks={filteredTasks}
                        onDelete={handleDeleteTask}
                        onUpdateStatus={handleUpdateStatus}
                        onEdit={handleEditTask}
                    />
                )}

            </main>
        </div>
    );
}
