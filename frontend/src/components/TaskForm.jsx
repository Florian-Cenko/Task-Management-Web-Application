import { useState } from "react";

export default function TaskForm({ onTaskAdded }) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [taskStatusEnum, setTaskStatusEnum] = useState("PENDING");

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!title.trim()) return;

        await onTaskAdded({
            title: title.trim(),
            description: description.trim(),
            taskStatusEnum
        });

        setTitle("");
        setDescription("");
        setTaskStatusEnum("PENDING");
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4">

            <h2 className="text-2xl font-bold text-gray-800 mb-5">
                Create New Task
            </h2>

            <div>
                <label className="block text-sm font-medium mb-1">
                    Title
                </label>
                <input
                    type="text"
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                    placeholder="Enter task title"
                    required
                    className="w-full p-3 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none"
                />
            </div>

            <div>
                <label className="block text-sm font-medium mb-1">
                    Description
                </label>
                <textarea
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                    placeholder="Enter task description"
                    rows="3"
                    className="w-full p-3 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none"
                />
            </div>

            <div>
                <label className="block text-sm font-medium mb-1">
                    Status
                </label>
                <select
                    value={taskStatusEnum}
                    onChange={e => setTaskStatusEnum(e.target.value)}
                    className="w-full p-3 border rounded-lg"
                >
                    <option value="PENDING">PENDING</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="COMPLETED">COMPLETED</option>
                </select>
            </div>

            <button
                type="submit"
                className="w-full bg-blue-600 text-white p-3 rounded-lg hover:bg-blue-700 transition"
            >
                Create Task
            </button>

        </form>
    );
}